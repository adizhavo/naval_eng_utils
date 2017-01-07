# Naval engineers toolset

A set of tools used by naval engineers to check, manipulate and generate reports as csv files used in their work pipeline.

The system supports these operations :
* Comparison between two csv files
* Data duplication check

Each operation generates a csv file with the result in the specified output directory.

### Requirements
* [leiningen](http://leiningen.org/)
* [latest version of java](https://java.com/en/download/)

### Set up
After getting [leiningen](http://leiningen.org/), in the project directory run the command ```lein deps``` to get all the dependencies in place.

In the resources folder is possible to find the [config.json](https://github.com/adizhavo/naval_eng_utils/blob/master/resources/config.json) to handle basic project configuration.
The supported commands for the ```actions``` field in the file are:
>comparison

>duplication

### Build
In the project directory run ```lein uberjar``` and will create a standalone jar file in the target folder of the project.
Create an external directory and put in the jar file along with the folders used in the config.json file.

To execute the program run these simple bash files [here](https://github.com/adizhavo/naval_eng_utils/tree/master/helpers).
