# Mcon groovy client

## Usage

```groovy
def echoTest = Mcon.proxy('mcon.EchoTest', [timeout: 5000]);
println echoTest.echo('adsf');
```

## Config

```groovy
Mcon.serverHttpPort = 8124;
```
