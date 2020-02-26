## Prerequisite
---
- Java 8
- Gradle > 6.0.0

## Introduction
---
This repository combines Spring features and allows to easily boot up an app.  Some  versions of dependencies used to create this repository as below,
> Spring Boot Version : 2.1.3.RELEASE   
> Spring Cloud Version : Greenwich.SR2
> Spring Dependency Management Version : 0.6.1.RELEASE   
> QueryDsl Version : 4.2.1     
> Jackson Core Version : 2.9.8   
> Json Web Token Version : 0.9.0  
> Lombok Version : 5.0.0-rc2
    
## Features
---
- Spring-security support.
- Spring environment features implementation
- Spring feign central configuration
- Custom generic JPA repository implementation
	- Soft delete support
	- Custom sequence support
	- Custom data initializer support
- QueryDsl support
- Postgresql support
- Central exception handler for rest endpoint
- Utility

## TODO
---
 - [ ] Data initializer with xml scenario
 - [ ] NoSQL support
 - [ ] Unit test

## Usage
---
To use any project,
1. Libraries must be published in an artifactory
	- Local 
	- Maven Central
	- Self hosted artifact (nexus, artifactory etc.). 
2.  Example for local publication,
```groovy
gradle kuartz-auth-core:publishMavenJavaPublicationToMavenLocal
gradle kuartz-cloud-core:publishMavenJavaPublicationToMavenLocal
```

## Contributor
---
[logo]: https://avatars1.githubusercontent.com/u/10180684?s=460&v=4 "Kutay ÇELEBİ"




