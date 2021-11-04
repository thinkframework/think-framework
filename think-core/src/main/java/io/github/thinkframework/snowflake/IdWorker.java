/**
 * 无符号数  0 0000000  1 00000001
 * 原码
 * 有符号数   +0 0000000 +1 00000001
 *          -0 1000000  -1 10000001
 * 问题
 *          +0 0000000  +1 00000001
 *       +  -0 1000000  -1 10000001
 *       =  -0 1000000  -2 10000010
 * 反码 符号位不变,其余位置取反(针对负数)
 * 有符号数  0 0000000  1 00000001
 *          0 1111111 -1 1111110
 * 解决
 *          +0 0000000  +1 00000001
 *       +  -0 1111111  -1 11111110
 *       =  -0 1111111  -0 11111111
 * 问题
 *          两个0(负0)
 * 补码 +1 丢弃最高位(针对负数)
 * 有符号数    0 0000000  1 00000001
 *            0 0000000 -1 11111111
 * 解决
 *          +0 0000000  +1 00000001
 *       +  -0 0000000  -1 11111111
 *       =  +0 0000000  +0 00000000
 */
package io.github.thinkframework.snowflake;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 雪花算法
 * @author lixiaobin
 * @version 1.0.0
 * @since 1.0.0
 */
public class IdWorker {

    private static final Logger log = LoggerFactory.getLogger(IdWorker.class);

    private static final long twepoch = 1288834974657L;

    /**
     *
     */
    private static final long workerIdBits = 5L;

    /**
     * 数据中心
     */
    private static final long datacenterIdBits = 5L;
    /**
     *           -1 11111111 11111111 11111111 11111111 11111111 11111111 11111111 11111111
     * ^ (-1 << 5)) 11111111 11111111 11111111 11111111 11111111 11111111 11111111 11100000
     * =         31 00000000 00000000 00000000 00000000 00000000 00000000 00000000 00011111
     */
    private static final long maxWorkerId = -1L ^ (-1L << workerIdBits);

    /**
     *           -1 11111111 11111111 11111111 11111111 11111111 11111111 11111111 11111111
     * ^ (-1 << 5)) 11111111 11111111 11111111 11111111 11111111 11111111 11111111 11100000
     * =         31 00000000 00000000 00000000 00000000 00000000 00000000 00000000 00011111
     */
    private static final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);

    /**
     * 顺序位
     */
    private static final long sequenceBits = 12L;

    /**
     * = 12
     */
    private static final long workerIdShift = sequenceBits;

    /**
     * 17 = 12 + 5
     */
    private static final long datacenterIdShift = sequenceBits + workerIdBits;

    /**
     * 22 = 12 + 5 + 5
     */
    private static final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

    /**
     *           -1 11111111 11111111 11111111 11111111 11111111 11111111 11111111 11111111
     * ^ (-1 << 5)) 11111111 11111111 11111111 11111111 11111111 11111111 11110000 00000000
     * =       4095 00000000 00000000 00000000 00000000 00000000 00000000 00001111 11111111
     */
    private static final long sequenceMask = -1L ^ (-1L << sequenceBits);

    private long lastTimestamp = -1L;

    private long workerId;
    private long datacenterId;
    private long sequence;

    public IdWorker(long workerId, long datacenterId,long sequence) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        } else {
            this.workerId = workerId;
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0",maxDatacenterId));
        } else {
            this.datacenterId = datacenterId;
        }
        this.sequence = sequence;
        log.info("worker starting. timestamp left shift {}, datacenter id bits {}, worker id bits {}, sequence bits {}, workerid {}",
            timestampLeftShift, datacenterIdBits, workerIdBits, sequenceBits, workerId);
    }

    public IdWorker() {
        this(0L,0L,0L);
    }

    /**
     * 下一个id
     * @return id
     * @throws RuntimeException
     */
    public long nextId() throws RuntimeException {
        // 当前毫秒时间
        long timestamp = timeGen();

        // 时钟回拨
        if (timestamp < lastTimestamp) {
            log.error("clock is moving backwards.  Rejecting requests until %d.", lastTimestamp);
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        // 最后一毫秒时间不是当前毫秒时间
        if (lastTimestamp == timestamp) {

            //           x  xxxxxxxx xxxxxxxx xxxxxxxx xxxxxxxx xxxxxxxx xxxxxxxx xxxxxxxx xxxxxxxx
            // &       4095 00000000 00000000 00000000 00000000 00000000 00000000 00001111 11111111
            // = (-1 << 5)) 00000000 00000000 00000000 00000000 00000000 00000000 0000xxxx xxxxxxxx
            // 不大于4095,4096的结果为0
            sequence = (sequence + 1) & sequenceMask;
            // 大于4095跳入下一秒
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }
        // 最后一个ID的时间
        lastTimestamp = timestamp;
        // 0-|               timestamp                 | |data+worker|-|  sequence |
        // 0-0000000 00000000 00000000 00000000 00000000-00000000 0000-0000 00000000;
        return ((timestamp - twepoch) << timestampLeftShift) | //时间戳左移22位
        (datacenterId << datacenterIdShift) | // 数据中心左移17位
        (workerId << workerIdShift) | // 左移12位
        sequence; // 12位序列
    }

    /**
     * 下一毫秒时间
     * @param lastTimestamp
     * @return
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        // 当前毫秒时间小于最后一个ID的毫秒时间,一直等到当前毫秒时间不小于最后一个ID的毫秒时间
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        // 当前毫秒时间
        return timestamp;
    }

    /**
     * 当前毫秒时间
     * @return
     */
    protected long timeGen() {
        return System.currentTimeMillis();
    }
}
