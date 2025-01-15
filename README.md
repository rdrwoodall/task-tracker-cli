# TaskTrackerCli
A CLI task app

# To run locally
```bash
# no longer works now that project converted to a maven project
# running through intellij for now until I can generate proper command for terminal without all the excess from IntelliJ
java -cp out/production/TaskTrackerCli com.tasktracker.cli.Main add "Testing a string message"
```
The application generates a file `taskStore.json` in the home directory of the current user. This file is the local storage for the application.

To "reset" the app state just delete the json file.

# Technologies & Learnings
**Bold** = enhancements

- java
  - classes & OOP
  - exception handling
  - project structure
  - file/stream IO
  - **testing**
- command line application
- **application installer/deployment**
- **app logging**
- bash scripting
- Docker (for testing deployment of application onto a system other than my local)
- Maven (dependency management)

# Project Requirements
1. Create a new public repo on GitHub
2. Complete the project according to the requirements and push your code the GitHub repo
3. Add README.md file with instructions to run the project and the project page URL
4. Once done, submit your solution to help the others learn and get feedback from the community

# App Requirements
The application should run from the command line, accept user actions and inputs as arguments, and store the tasks in a JSON file. The user should be able to:

- Add, Update, and Delete tasks
- Mark a task as in progress or done
- List all tasks
- List all tasks that are done
- List all tasks that are not done
- List all tasks that are in progress

## Constraints to Guide the Implementation:

- You can use any programming language to build this project.
- Use positional arguments in command line to accept user inputs.
- Use a JSON file to store the tasks in the current directory.
- The JSON file should be created if it does not exist.
- Use the native file system module of your programming language to interact with the JSON file.
- Do not use any external libraries or frameworks to build this project.
- Ensure to handle errors and edge cases gracefully.

## Task Properties

Each task should have the following properties:
```
{
    id: A unique identifier for the task
    description: A short description of the task
    status: The status of the task (todo, in-progress, done)
    createdAt: The date and time when the task was created
    updatedAt: The date and time when the task was last updated
}
```
## Possible enhancements
- Bring in terminal prompt dependency for nice command line prompts/formatting/output/etc.
- Create an "installer" for this tool (mac/linux/windows?)
- Logging 
  - SLF4J (Simple Logging Facade For Java) + LogBack
  - or
  - Log4J2
- Run a preview/diff before updating file if flag is present