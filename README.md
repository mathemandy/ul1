

![Android Build](https://github.com/mathemandy/ul1/workflows/Android%20Build/badge.svg)

This project contains an implementation of MVVM based on requirement for an Assessment 


## Features
* Clean Architecture with MVVVM
* Kotlin Coroutines with Flow
* Dagger Hilt
* GitHub actions for CI

## Prerequisite
To build this project, you require:
- Android Studio 4.2 beta or higher
- Gradle 6.5



- The App is broken down into four main modules 
* app
* core
* features
* libraries

This abstraction enables me to separate concern of the project into separate Module
#### Remote 
- The remote layer is responsible for fetching the data from the remote source.
  it can be found under libraries/remote
  
#### Local 
- The local layer is responsible for persisting our data for offline capabilities. 
- Room was used here as the choice of Persistence Library.
- Two tables were create -: Subjects and Lesson Table 
- Subject Table handles the subject data while the Lesson Table handles the recently watch Video 
- A Special Query was done to retrieve the Lesson and Subjects using a one to one relation, I leveraged Room's @Relation annotation function to retrieve this objects. A new data object LessonAndSubjects holds the result of this query

  
#### Data

 

