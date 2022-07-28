package models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/*
 * POJO for an Message object
 *
 *   {
    "sequence": "-",
    "timestamp": "_",
    "fromid": "xt0fer",
    "toid": "kristofer",
    "message": "Hello, Kristofer!"
  },

*
 */
@JsonPropertyOrder({"seqId", "timestamp", "fromId", "toId", "message"})
public class Message implements Comparable {

    @JsonProperty("message")
    protected String message = "";
    @JsonProperty("toid")
    protected String toId = "";
    @JsonProperty("fromid")
    protected String fromId = "";
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String timestamp = "";
    @JsonProperty("sequence")
    protected String seqId = "";

    public Message (String message, String fromId, String toId) {
        this.message = message;
        this.fromId = fromId;
        this.toId = toId;
    }

    public Message(){};

    public Message (String message, String fromId) {
        this.message = message;
        this.fromId = fromId;
        this.toId = "";
    }

    @Override
    public String toString() {
        return "to: " + this.toId + "\nfrom: "+ this.fromId + "\n" + this.message + "\n----\n";
    }

    public int compareTo(Object o) {
        return this.seqId.compareTo(((Message) o).getSeqId());
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public String getTimestamp() {
        return timestamp;
    }
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public void setTimestamp(String time) {
        this.timestamp = time;
    }

    public String getSeqId() {
        return seqId;
    }
}