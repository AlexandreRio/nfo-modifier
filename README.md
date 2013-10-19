nfo-modifier
=======

nfo-modifier is a Java application that allows the modification of .nfo files (i.e. encoded in cp437 )
and provides several tools to ease the creation and manipulation of nfo.

You can have a look at the documentation : `doc/index.html`.

For the record I have nothing to do with the `exemple.nfo` file, I just picked it with google.

![example.nfo file](https://raw.github.com/AlexandreRio/nfo-modifier/master/interface.png)

Usage
-------

The source might not be stable, so I recommend to download a stable release [here](https://github.com/AlexandreRio/nfo-modifier/releases), then simply launch nfo-modifier.jar with a Java Runtime Environment > 1.7

If you prefer building from the sources, you will need to use ant.

    $ ant run

Compiles the sources and run the application in graphical mode

    $ ant jar

Creates a .jar of the application named `build/nfo-modifier.jar`, after that
the rest of the folder can be deleted. All the necessery files are included in
the jar archive.

    $ ant -p

Displays all the available tasks.

    $ ant help

Displays the help.

    $ java -jar nfo-modifier.jar

Launch the application with an empty text area.

    $ java -jar nfo-modifier.jar -f file.nfo

Launch the application and display the file : `file.nfo`.

Command line interface
-------

I'm currently working on making available all the features via the command line interface.

The main purpose is to make something like that possible :

    $ java -jar nfo-modifier.jar --no-gui --load-profile profiles.data --profile Wii --content content.txt --output-file coolNFO.nfo

And a short version with the same effect :

    $ java -jar nfo-modifier.jar -ng -lp profiles.data -p Wii -c content.txt -o coolNFO.nfo

For now these are the supported argument :

    --no-gui         : do not use the graphical user interface
    --create-data    : create a new empty data profile file
    --load-data      : load a previously created profiles data file
    --list           : List all the profile stored in the loaded profiles data file
    --profile        : specify the profile to use
    --content        : specify the content of the nfo, it will be placed in the profile
    --output-file    : output file to save the nfo, so cp437 coded, if not specified the standard output will be used, in utf8
    --help           : just say you use the argument --help, will display actual help soon
    --silent         : don't print any messages
    --create-profile : create a new profile with 5 options, like --create-profile MyProfile header.txt border.txt body.txt footer.txt
    --delete-profile : delete an existing profile

And the following arguments are going to be supported in the next updates :

    --verbose

Features list
-------
* Read and write nfo files,
* Create profiles to manage templates,

TODO list
-------

* Sort of status bar with : current longest line, number of lines,
* Tools for text manipulation, move a text of a column towards the right/left,

Special Thanks
-------

Thanks to [tips4java](http://tips4java.wordpress.com/) for the text line
number under a really permissive license.
