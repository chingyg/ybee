# bumble.

## Requirements
- [Java 8 JRE](http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html)

## How to run

Make sure you are at the root of Bumble directory
```shell
java -jar target/bumble-deployed.jar -c
```

## Changes Log

### 1.6.0
- Converted project to maven project.
- Removed external selenium jars. Maven will handle dependencies now.
- Removed the deployed folder, executable jar is now located in target.
- Updated and refined the way offers are completed.