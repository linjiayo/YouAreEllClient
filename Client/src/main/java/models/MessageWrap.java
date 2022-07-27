package models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"seqId", "time", "fromId", "toId", "message"})
public class MessageWrap extends Message {
    @JsonProperty("time")
    private String time = "";
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String timestamp = "";

    public MessageWrap (String message, String fromId, String toId) {
        this.message = message;
        this.fromId = fromId;
        this.toId = toId;
    }

    @JsonProperty("time")
    public String getTime() {
        return time;
    }

    @JsonProperty("time")
    public void setTime(String time) {
        this.time = time;
    }
}
