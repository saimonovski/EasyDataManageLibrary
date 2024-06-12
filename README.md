# Implementation
you must insert *version* the version from [repository-central]{https://central.sonatype.com/artifact/io.github.saimonovski/DataManage/versions}
-  ## Maven
```
<dependency>
    <groupId>io.github.saimonovski</groupId>
    <artifactId>DataManage</artifactId>
    <version>version</version>
</dependency>
```
-  ## Gradle
  ```
implementation group: 'io.github.saimonovski', name: 'DataManage', version: 'version'
```
# Usage
First select type of handler & create instance of DataManager 
`YamlHandler yamlData = DataManager.createInstance("something type here);`
list of types:
- YamlHandler - handler for yaml files
- MYSQLHandler - handler for mysql database
  full method should look like this
  ```
  public void exampleMethod() throws IOException {
        //example of creating manager for yaml files
            YAMLHandler yamlManager = DataManager.createInstance("config.yml");
        // or
        // example of create Mysql manager
            MYSQLHandler  mysqlManager = DataManager.createInstance()// <-- database login data inside the args
  ```

  # javadocs
  
