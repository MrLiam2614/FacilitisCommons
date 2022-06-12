## Facilits Commons

To include our libraries to your plugin you need to add this to your pom.xml:
<br><br>

````
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>


<dependency>
    <groupId>com.github.MrLiam2614</groupId>
    <artifactId>FacilitisCommons</artifactId>
    <version>1.0.0</version>
</dependency>
````

You can shade our plugin into yours using this:

````
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-shade-plugin</artifactId>
    <version>3.2.4</version>
    <executions>
        <execution>
        <phase>package</phase>
            <goals>
                <goal>shade</goal>
            </goals>
            <configuration>
                <createDependencyReducedPom>false</createDependencyReducedPom>
            </configuration>
        </execution>
    </executions>
</plugin>
````

<hr>

## Index

<a href="#description">Description</a><br>

<hr>

<div id="description">

## Description

</div>
