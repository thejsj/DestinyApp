# Destiny App

## Setup

1. Add `JAVA_HOME` to your PATH

In your `.bash_profile`, add the following:

```
export JAVA_HOME=$(/usr/libexec/java_home)
```

2. Install Spring and Maven.

If you're on mac, you can use Homebrew.

```
brew tap pivotal/tap
brew install springboot
brew install maven
```

## Installing App

1. Validate

Use Maven to validate your app.

```
mvn validate
```

2. Compile

```
mvn compile
```

3. Run

```
java -jar target/endorsed/javaee-endorsed-api-7.0.jar
```

