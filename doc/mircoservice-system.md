
* what i write is glue - services/apps that interface any external parts to single control ui and repl logic
* stream whatever data from wahter parts into db and control them from ui, repl as assimilated components
* ui, querying must be easy to create, not pain
* the should be a tempalte 'bot' clojure app/service taht you take and modify to talk to new component
* glue puts data into db, presents it let you control things via ui and repling to glue serivces
* the key difference from eos idea is that the focus is on being able to assimilate anything, using the technology as is; 
  to come up with new tech that is real requires growth and progress
* all ui and control apps should be craeted anew, limiting(or even exluding) other tools to favour glue as 
  unifying and consistent basis of the system
* the clojure - bash relationship is important
* web interface should allow for a lot

<br />

* the key ot success is to achive end user product and evolve glue as a consequence
* focusing on glue as product will lead to failure

# things to consider

* repl from browser to ui app and to the system/system tool (from engineer ui)
* historic data, versioning - it should be datomic alike
* core functinoality of the system
* search
* not a dead system,an engaging system

# documentation

* it everything
* versioning: all versions docs should be always available
* but docs comes after the system starts solving problems and answering questions
* system value outwheighs growth issues

# idea driven

* features should come from systemic-design approach, not from users (only suggestions, engineers choose/decide)

# product

* goal - make a product
* it either succeeds, or can be used to find a host company


# features lacking in github + google docs combo

* history in split view allowing to fast-see versions and diffs
* versions of text pragraph/unit (not only files)
  easily scrollable or seeable in split view 
* graph view of all linked text units, docs
* replay: show (in graph or src/tree) 'movie' of how files/units evolved over time
* queries like
  - show me all commits about ...
  - scope of smbd's commits from _ to _
* arbitrary queries against graph (code or text units)
  - all fns that lead to invokation of somefn
  - graph of files
  - graph of fns
  - dependency graph all the way down to language and OS


# 12 releases

user can:

1

* login, create public/private projects, teams, organizations
* create records
* comments, notes
* repl w/ arbitrary queries

2

* edit files: verioning, units, links

3

* search

4

* graph of records, units, files

5

* git repo import
* git queries

6

* clj code units
* clj graphs of fns, files, deps

7

* queries and forming graphs, ui with queries 

8

* extensions 

9

* records, units -> export as static apps/file

10

* github, lab, bucket integrations: sync repos, publish apps

11

* replay

12

* ml: find patterns, suggestions (changes that were made that might interest you)

