import Mcon;

def msg = Mcon.invoke([
    serviceId: 'mcon.EchoTest',
    methodName: 'echo',
    params: ['123']
]);
println msg;

msg = Mcon.invoke([
    httpMethod: 'get',
    serviceId: 'mcon.ClientEchoTest',
    methodName: 'echo',
    params: ['456']
]);
println msg;

def echoTest = Mcon.proxy('mcon.EchoTest');
println echoTest.echo('你好');
