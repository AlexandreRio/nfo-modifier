nfo-modifier
=======

nfo-modifier allow the modification of .nfo file (i.e. encoded in cp437 ).

You can have a look at the documentation : `doc/index.html`.

For the record I have nothing to do with the `exemple.nfo` file, I just picked it with google.

<img src="http://pix.kegtux.org/images/2012/12/09/Vxa8G.png" alt="exemple.nfo file"/>

Usage
-------

    $ ant run

Compiles the sources and run the application in graphical mode

    $ ant jar

Creates a .jar of the application named `build/nfo-modifier.jar`, after that
the rest of the folder can be deleted. All the necessery files are included in
the jar.

    $ ant -p

Displays all the allowed tasks.

    $ ant help

Displays the help.

    $ java -jar nfo-modifier.jar

Launch the application with an empty text area.

    $ java -jar nfo-modifier.jar file.nfo

Launch the application and display the file : `file.nfo`.

Special Thanks
-------

Thanks to [tips4java](http://tips4java.wordpress.com/) for the text line
number under a really permissive license.


TODO list
-------

* Template generator,
* Profile manager with defined file header,
* Sort of status bar with : current longest line, number of lines,
* Tools for text manipulation, move a text of a column towards the right/left,
