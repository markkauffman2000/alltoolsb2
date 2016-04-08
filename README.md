alltoolsb2
=====================

This project is based from the Spring Tool Suite (STS) Spring MVC project. We've added a bb-manifest.xml and an example of a view that uses the Blackboard Tags to render a page that has the look and feel of other Blackboard Learn pages.

### Clone to local repo
####bitbucket: 
git clone https://markkauffman2000@bitbucket.org/markkauffman2000/alltoolsb2.git alltoolsb2

### Building
To build the project, just run:

./gradlew build

### Deploying
To deploy the B2 to your Learn server, run:

./gradlew -Dserver=host:port deployB2

Example: ./gradlew -Dserver=localhost:9876 deployB2

### Handy Vagrant Commands
vagrant ssh is equivalent to:
ssh -p 2222 vagrant@127.0.0.1
or
ssh -p 2222 vagrant@localhost

So we can use scp to copy stuff in.
scp -P 2222 stuff vagrant@127.0.0.1:/home/vagrant/stuff

Or - just as easy, anything we place in the directory where we started vagrant with 'vagrant up' will be placed in:
/vagrant on the guest system.

Use the following to insure good permissions/ownership on files in blackboard directory.
find /usr/local/blackboard -print -exec chown bbuser {} \;