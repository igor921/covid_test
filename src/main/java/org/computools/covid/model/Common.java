package org.computools.covid.model;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//Common data structure from API representation
public class Common<T> {

    @SerializedName("All")
    protected T data;

}
