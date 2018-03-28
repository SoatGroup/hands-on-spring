# Reactive Demo

## Goal :

* Discover Reactive Streams API
* Write test with reactive streams assertions

## How to :

* Implement class `MetricService` to monitor the memory
  * Read file `/proc/meminfo`
  * Filter on the correct line
* Improvements :
  * Close resource properly (look at [Try](https://static.javadoc.io/io.vavr/vavr/0.9.1/io/vavr/control/Try.html) method if you want to use lambda)
  * Show other metrics
  
  
## How to build an USB key from this code repository?

- run the command `buildRepository.sh`
- copy all files + the directory `repository`
- voilà!  