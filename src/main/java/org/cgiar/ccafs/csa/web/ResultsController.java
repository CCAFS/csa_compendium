package org.cgiar.ccafs.csa.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RestController;

import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@RestController
@ManagedBean
@Scope("view")
public class ResultsController implements Serializable {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private List<Integer> items;

    public List<Integer> getItems() {
        if (items == null) {
            Integer[] nums = {1334, 29582, 3463, 45546, 67139, 72115, 88459, 9412, 24424, 2222, 34456, 44546};
            items = Arrays.asList(nums);
        }
        return items;
    }

}
