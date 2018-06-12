<img src="/assets/inviacodechallenge.gif">

# Github Code Challenge

An Android Application for Github Repository Management.

This project aims to show a list of Github Repositories and its subscribed users upon search.
It is written in Kotlin.

#### Code Design and Architectural Solution

The framework made for this challenge was initially made with the MVP pattern in mind. It also abides in CLEAN Architecture with SOLID principles (To meet up the universal rules requirements).
Unit testing were also created for checking business and logic rules.

<b> Task description Requirements: </b>

    The App will query the web service GET https://api.github.com/search/repositories with certain queries to search for different repositories
    The apps' current Mininum SDK requirement is 19 to be compatible with Android 4.4 till the latest version
    Uses MVP together with CLEAN persistence layers
    Uses Material Design patterns principles for modern designs
    Uses Recycler View, Constraint Layout, Picasso Image Loader for best UI performance
    Best Libraries used: Dagger, Retrofit2, RxJava2, Okhttp, and Kotlin
    App runs both in portrait and landscape mode
    Although not a library itself, I've added some extension classes (AnimationExtension and UiUtils). Its some of the collaboration of my work together my previous colleagues to shorten layout creation.

<b> Mandatory stage </b>

    Query the web service: GET https://api.github.com/search/repositories with the following parameters:
        • Query
        • Page
        • List per page

    Display the list of repositories with the following parameters:
        •  The avatar image of the owner
        •  The repository name
        •  The description
        •  The number of forks
        •  The programming language
        •  The last update

<b> Optional stage </b>

    Once a user clicks one of the repository card, a new activity will show the following:
        • The name of the repository
        • The subscribers count
        • The list of subscribers
        • The subscribes avatar

<b> Bonus stage </b>

    Unit Tests were added

Libraries Used:

	Kotlin
	Retrofit
	Dagger 2
	RxJava
	Gson
	Rounded Image View
	Picasso Image Loader
	SpinKit

Classes Used:

Config:	- handles the class dependcies and main application class.
	MainApplication

	Dagger Components:
		Activities Component

	Dagger Modules:
		ThreadModule
		MapperModule
		RepositoryModule
		ServiceModule

Mapper : - handles the mapping of the response to the business objects.

	DataMapper - mapper interface
	DataMapperImpl - DataMapper implementation. Trasforms the response object to a business object.

Models : - handles all the stored objects to used all over the app

	Owner - handles the Github Owners and Subscribers
	OwnerResults - the Owner response object
	Pagination, Link - Handles Pagination information
	Repository - the Repository Business object information
	RepositoryResponse, Repository Results - handles the Respository response object
	RepositoryViewHandler - handles the Repository object together with Pagination
	Subscriber - the Subscriber Business Object
	SubscriberResults - handles Subscriber response object
	SubscriberViewHandler - handlers the Subscriber object together with Pagination

Repositories : - handles the data source of the CLEAN layer.

	GithubRepository - repository interface
	GithubRepositoryImpl - GithubRepository implementation. Managers rest endpoint calls and maps response objects to businesses.

Rest : - part of the data source where CLEAN layer handles the classes needed for the REST Api.

	GithubRepositoryRest - holds the response object from REST Api.

Schedulers :- handles thread Scheduling for the REST calls.

	ThreadScheduler - interface scheduler
	ThreadSchedulerImpl - thread class used for handling RxJava observation and subscription thread.

Services :- handles the services to be used for the app. Such as the database

	DataMapper - data mapper interface
	DataMapperImpl - Mapping service from response to business object
    DateUtil - handles date services
    DefaultParcelable - handles objects serialization and parcelization
    PaginationHeaderParser - parses pagination information from header
    PicassoImageLoader - handles network image viewing

UI :- ui class that handles the views for the app

	MainActivity - activity class used for viewing the responses and behavior of the app
	MainAdapter - pager adapter class to handle card views that would be needed to show different repositories
	MainPresenter - handles the decison making on which view will be seen on the activity
	MainView - a callback interface that is used by the presenter to show which views will be used
	SubscriberActivity - acitivity class that handles all subscriptions
	SubscriberPresenter, SubscriberView, SubscrberAdapter - same as above

Unit Testing :- Automated unit test

    DataMapperTest - tests if response objects are correctly aligned to their business objects
    MainPresenterTest - tests if the presenter correctly shows the right view on certain conditions

Improvements

    Given more time, I would have made more changes in the following:
    - refactoring on the OnScrollListeners and seperating them from the acitivity
    - improve more on the UI especially on textview that shows the owner and subscribers names
    - put more tests
    - creating a CI to test and deploy

