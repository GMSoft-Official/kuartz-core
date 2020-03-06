[![Build Status](https://travis-ci.com/GMSoft-Official/kuartz-auth-server.svg?branch=dev)](https://travis-ci.com/GMSoft-Official/kuartz-auth-server) [![Codacy Badge](https://api.codacy.com/project/badge/Grade/dfde0a27a2074adc84e54eeebc15dabf)](https://www.codacy.com/gh/GMSoft-Official/kuartz-auth-server?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=GMSoft-Official/kuartz-auth-server&amp;utm_campaign=Badge_Grade)

# Kuartz Authorization Server

This repository, which is made for authorization between client and resource, uses Spring Security features and OAuth 2.0. This application is intended to be used in resources app. The authorization server authenticate via JWT tokens which are stored in [`JwtTokenStore`](https://docs.spring.io/spring-security/oauth/apidocs/org/springframework/security/oauth2/provider/token/store/JwtTokenStore.html).

# Prerequisites
 1. PostgreSQL (we preferred postgresql docker image)
 2. Java > 1.8
 3. Gradle > 6.0

# TODO
- [ ] Dockerize authorization server.
- [ ] SSO support
- [ ] Redis support for token store
- [ ] Link microservices
- [ ] Docker profile

# Structure
todo (client module, service module)

# Usage

todo (swagger, endpoint, how to works.)

## Contributor

[![kutaycelebi](https://avatars1.githubusercontent.com/u/10180684?s=96&v=4)](https://github.com/kutay-celebi) [![azizerel](https://avatars0.githubusercontent.com/u/9365541?s=96&v=4)](https://github.com/azizerel)
