This is my first project ever !

# About

I discovered computer science by taking a random class, "Programming in Java", a title in which the only word I understood was "in".
This was a revelation. I read the "Java 6" book from start to finish, doing all the exercises thoroughly every evening.
By the end of my lecture, I was ready for a real project. This is it.

# Changes

When a stumbled upon this old repository, I wanted to keep it safely in git, but didn't want to change it too much.
This is history after all. Hence, my only changes since the actual writing in late 2011 are:

* run *format* on every file in IntelliJ
* ensure we can easily load ascii art either from jar or from an external file
* add a gitignore + a tiny script to automatically create the jar

# Compile and run

**Compile**
```bash
./build-jar.sh
```

**Run**
```bash
java -jar derlin-FirstProject.jar
```

**Arguments**

Passing an ascii file as first argument will replace the default heart. There are also readily available other designs.
This is the list (use it by passing <name.txt> as first argument):

* `coeur.txt`: a heart
* `coeur2.txt`: another heart
* `dessin_tete_mort.txt`: a skull head
* `tete_mort.txt`: another skull head
* `dragon.txt`: a dragon
* `dragon_large.txt`: a big dragon
* `lapin.txt`: a bunny
* `pas.txt`: footprints


