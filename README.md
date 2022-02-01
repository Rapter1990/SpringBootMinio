# Spring Boot Minio

<img src="screenshots/springboot_minio.png" alt="Main Information" width="800" height="300">

### 📖 Information

<ul style="list-style-type:disc">
  <li>MinIO is the only object storage suite available on
      every public cloud with supporting high-performance.</li>
  <li>It is API compatible with Amazon S3 cloud storage service</li>    
  <li>It can handle unstructured data such as photos, videos, log files, backups, and container images with (currently) the maximum supported object size of 5TB.</li>
  <li>Here is the explanation of the project
      <ul>
        <li>Implement the process of uploading file to Minio</li>
        <li>Implement the process of downloading file from Minio</li>
        <li>Implement the process of adding bucketname to Minio</li>
        <li>Implement the process of listing all file from bucketname in Minio</li>
        <li>Implement the process of listing all bucketnames in Minio</li>
        <li>Implement the process of deleting bucketname from Minio</li>
        <li>Implement the process of deleting file list from bucketname from Minio</li>
        <li>Implement the process of deleting file from bucketname from Minio</li>
      </ul>
  </li>
</ul>

### 🔨 Run the App

<b>1 )</b> Install <b>Docker Desktop</b>. Here is the installation <b>link</b> : https://docs.docker.com/docker-for-windows/install/

<b>2 )</b> Open <b>Terminal</b> under <b>resources</b> folder to run <b>Minio</b> on <b>Docker</b> Container
```
    docker-compose up -d
```
<b>3 )</b> Open <b>Minio</b> in the Browser 
```
    127.0.0.1:9001
```
<b>4 )</b> Enter username and password 
```
    username : minioadmin
    password : minioadmin
```

### Screenshots

<details>
<summary>Click here to show the screenshots of project</summary>
    <p> Figure 1 </p>
    <img src ="">
    <p> Figure 2 </p>
    <img src ="">
</details>