package javaModel;

public class HttpResponse {

    private Boolean dbConfirmation;
    private String dbErrorString;

    public Boolean getDbResponse() {
        return dbConfirmation;
    }

    public void setDbResponse(Boolean dbResponse) {
        this.dbConfirmation = dbResponse;

    }

    public HttpResponse() {
        dbConfirmation=false;
        dbErrorString="";
    }

    public Boolean getDbConfirmation() {
        return dbConfirmation;
    }

    public void setDbConfirmation(Boolean dbConfirmation) {
        this.dbConfirmation = dbConfirmation;
    }
}
