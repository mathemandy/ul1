

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

- ##NB: 
  The Icon field in the object was used throughout the app for the images which seemed different from what was on the design.  

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
- This layer holds the repository class. Its holds the logic that combines the data from the remote layer and the local layer. 
- A inline function  `networkBoundResource` is responsible for handling the logic of offline first, then remote. 
- The function emits a Loading signal first, the tries to get data from local and emits it to the UI. After that it tries to fetch new content from the remote source based on the result in ``shouldFetch` block.
- This data received from the network is stored directly to the db and emitted back to the UI again. 

#### Domain
- This layer holds interfaces  of implementation classes, models  and  useCases.

#### Feature/home/subjects 
- This is a Dynamic Feature Module that holds only a fragment. 
- The data that shows on the UI is gotten from the cache. 
- It shows data based on statistic - EMPTY , LOADING , OFFLINE,  LOADING_WIH_DATA and FAILED
- the appropriate Ui is shown depending on the state of the data. 
- Navigation Component is used to open the next Screen  - Lessons and the object is passed as a bundle. 
- Two Recyclerviews were used in this  Screen. 


#### Feature/home/Lessons 
- This is a Dynamic Feature Module that holds only a fragment (SubjectFragment). 
- Data to this Screen is gotten as a bundle from the previous screen. 
- `Groupie` library is used in this screen to render the sections based on the objects that was provided. 
- A viewmodel was used here, since its always guaranteed that we get data as a bundle from the previous screen. 
- But this is open for improvement as time was a constraint for the task. 
- One Recyclerview was used in this screen and the library handled how smaller components were added on the screen. 
- Coordinate Layout was used as parent layout to give the toolbar animation on this screen and is subject to change based on preference. 
- The Lesson Object is passed to the LessonDetail screen as a bundle using Navigation Component. 


#### Feature/home/LessonDetail 
- This is a Dynamic Feature Module that holds only a fragment (LessonDetailFragment).
- Data to this Screen is gotten as a bundle from Lessons Fragment. 
- The Component for the Player was a library which i added as a module to be able to customize correctly.  
- I chose to save the `Lesson` data to the database on oncreate, so that happens only once except if the app is left and android decides to kill the fragment. 
- I added support for landscape for the video. 
- The user has to switch on auto rotation on the device and then switch to fullscreen  by rotating the device. 
- Video Playback does not stop while switching which is a big plus. 
- More improvement can be done on this. 


