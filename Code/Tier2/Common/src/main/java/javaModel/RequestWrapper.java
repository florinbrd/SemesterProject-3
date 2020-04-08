package javaModel;

import java.io.Serializable;
import java.lang.reflect.Type;

public class RequestWrapper implements Serializable {


    private Login obj1;
    private Session obj2;

    public RequestWrapper() {

    }

    public RequestWrapper(Login obj1, Session obj2) {
        this.obj1 = obj1;
        this.obj2 = obj2;
    }

    public Login getObj1() {
        return obj1;
    }

    public void setObj1(Login obj1) {
        this.obj1 = obj1;
    }

    public Session getObj2() {
        return obj2;
    }

    public void setObj2(Session obj2) {
        this.obj2 = obj2;
    }
}
