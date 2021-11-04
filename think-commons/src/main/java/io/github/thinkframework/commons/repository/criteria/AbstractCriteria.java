package io.github.thinkframework.commons.repository.criteria;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Data
public class AbstractCriteria {

    /**
     * 是否分页
     */
    @Builder.Default
    protected Boolean paged = false;

    protected Integer page;
    protected Integer size;

    protected String[] sort;


    public boolean isPaged() {
        return paged;
    }


    public boolean isUnpaged() {
        return !this.isPaged();
    }

    public void setPaged(Boolean paged) {
        this.paged = paged;
    }

    public Pageable paged(){
        if(isUnpaged()){
            return Pageable.unpaged();
        }

        return new PageRequest(page,size,sort == null ? Sort.unsorted() : Sort.by(sort)){

        };
    }
}
