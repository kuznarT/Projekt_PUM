
package com.example.kuznargroup.welowanie;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Question {

    @SerializedName("question")
    @Expose
    private String question;
    @SerializedName("answer1")
    @Expose
    private String answer1;
    @SerializedName("answer2")
    @Expose
    private String answer2;
    @SerializedName("answer3")
    @Expose
    private String answer3;
    @SerializedName("answer4")
    @Expose
    private String answer4;

    /**
     * 
     * @return
     *     The question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * 
     * @param question
     *     The question
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * 
     * @return
     *     The answer1
     */
    public String getAnswer1() {
        return answer1;
    }

    /**
     * 
     * @param answer1
     *     The answer1
     */
    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    /**
     * 
     * @return
     *     The answer2
     */
    public String getAnswer2() {
        return answer2;
    }

    /**
     * 
     * @param answer2
     *     The answer2
     */
    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    /**
     * 
     * @return
     *     The answer3
     */
    public String getAnswer3() {
        return answer3;
    }

    /**
     * 
     * @param answer3
     *     The answer3
     */
    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    /**
     * 
     * @return
     *     The answer4
     */
    public String getAnswer4() {
        return answer4;
    }

    /**
     * 
     * @param answer4
     *     The answer4
     */
    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }

}
