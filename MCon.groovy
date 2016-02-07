import groovy.json.*;
import jodd.http.*;

class MconProxy extends groovy.util.Proxy {
    def serviceId = '';
    def invokeMethod(String methodName, Object args) {
        def res = Mcon.invoke([
            serviceId: serviceId,
            methodName: methodName,
            params: args
        ]);
        return res;
    }
}

class Mcon {
    static int serverHttpPort = 8124;

    static def invoke(conf) {
        def url = "http://localhost:$serverHttpPort/proxy/invoke";
        def req = conf.httpMethod == 'get' ? HttpRequest.get(url) : HttpRequest.post(url);
        def res = req
    		.formEncoding("UTF-8")
            .form('headers', new JsonBuilder(conf.headers ?: [:]).toString())
            .form('serviceId', conf.serviceId)
            .form('methodName', conf.methodName)
            .form('params', new JsonBuilder(conf.params ?: []).toString())
    		.send();
        def body = new JsonSlurper().parseText(res.body());
        if (body.success) {
            return body.response;
        } else {
            throw new Exception('' + body.error);
        }
    }

    static def proxy(serviceId) {
        return new MconProxy(serviceId: serviceId);
    }
}
