# 프로젝트 명세서 (vibeapp)

본 문서는 JDK 25 및 Spring Boot 4.0.1 기반의 최소 기능 웹 애플리케이션 프로젝트인 'vibeapp'의 설정을 정의합니다.

## 1. 프로젝트 개요
- **프로젝트 명**: vibeapp
- **설명**: 최소 기능 스프링부트 애플리케이션을 생성하는 프로젝트입니다.
- **주요 목표**: 최신 기술 스택(JDK 25, Spring Boot 4.x)을 활용한 안정적인 기반 환경 구축.

## 2. 기술 스택 (Technical Stack)
| 구성 요소 | 상세 내용 |
| :--- | :--- |
| **JDK** | JDK 25 이상 |
| **Language** | Java |
| **Spring Boot** | 4.0.1 이상 |
| **Build Tool** | Gradle 9.3.0 이상 |
| **Gradle DSL** | Groovy DSL |

## 3. 프로젝트 메타데이터 (Metadata)
- **Group**: `com.example`
- **Artifact**: `vibeapp`
- **Main Class Name**: `VibeApp`
- **Configuration Format**: YAML (`application.yml`)

## 4. 빌드 설정 (Build Configuration)
### Plugins
- `org.springframework.boot` (version 4.0.1)
- `io.spring.dependency-management` (Spring Boot 버전에 맞춤)
- `java`

### Dependencies
- **최소 구성**: 별도의 외부 종속성 없이 Spring Boot Web Starter를 기본으로 포함할 수 있으나, 현재 요청에 따라 명시적인 추가 종속성은 없음.

## 5. 프로젝트 구조 (예시)
```text
vibeapp/
├── gradle/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/vibeapp/
│   │   │       └── VibeApp.java
│   │   └── resources/
│   │       └── application.yml
├── build.gradle
└── settings.gradle
```
