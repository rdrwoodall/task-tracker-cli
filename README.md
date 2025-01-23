# TaskTrackerCli

This is a project from [roadmap.sh](https://roadmap.sh/projects/task-tracker), only thing different is I used external
dependencies for json file processing.

A CLI task app to refresh java and play with maven/docker/etc.

It's been years since I really touched java, so I'm refreshing it with small projects like this.

I'm adding in tests/build/deploy steps as well to update myself with the ecosystem a bit more.

# To run locally

```bash
# the <project-directory> is the directory you cloned this project in from GitHub
# typically the <maven-package-directory> is stored in the home directory of the user

> java -classpath /<project-directory>/task-tracker-cli/target/classes:/<maven-package-directory>/.m2/repository/com/google/code/gson/gson/2.11.0/gson-2.11.0.jar:/<maven-package-directory>/.m2/repository/com/google/errorprone/error_prone_annotations/2.27.0/error_prone_annotations-2.27.0.jar com.tasktracker.cli.Main list
```

The application generates a file `taskStore.json` in the home directory of the current user. This file is the "local
storage" for the application.

To "reset" the app state just delete the json file.

# Technologies & Learnings

- [ ] java
    - [x] classes & OOP
    - [x] exception handling
    - [x] project structure
    - [x] file/stream IO
    - [ ] testing
- [x] command line application
    - [ ] terminal prompt dependency for cleaner command line prompts/output formatting/etc.
- [ ] application installer/deployment (linux/mac/windows)
- [ ] logging (SLF4J (Simple Logging Facade For Java) + LogBack or Log4J2)
- [ ] bash scripting
- [ ] Docker (for testing deployment of application onto a system other than my local)
- [x] Maven (dependency management)

## Potential enhancements

- [ ] Run a preview/diff before updating file if flag is present
