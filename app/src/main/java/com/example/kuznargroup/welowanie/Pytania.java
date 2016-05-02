
package com.example.kuznargroup.welowanie;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Pytania {

    @SerializedName("success")
    @Expose
    private int success;
    @SerializedName("question")
    @Expose
    private Question question;

    /**
     * 
     * @return
     *     The success
     */
    public int getSuccess() {
        return success;
    }

    /**
     * 
     * @param success
     *     The success
     */
    public void setSuccess(int success) {
        this.success = success;
    }

    /**
     * 
     * @return
     *     The question
     */
    public Question getQuestion() {
        return question;
    }

    /**
     * 
     * @param question
     *     The question
     */
    public void setQuestion(Question question) {
        this.question = question;
    }

}
