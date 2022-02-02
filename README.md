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
<b>5 )</b> Explore Rest APIs
<table style="width:100%">
  <tr>
    <th>Method</th>
    <th>Url</th>
    <th>Description</th>
    <th>Valid Request Body</th>
    <th>Valid Request Params</th>
  </tr>
  <tr>
    <td>POST</td>
    <td>/upload</td>
    <td>Upload file to Minio</td>
    <td>[Info](#upload)</td>
    <td></td>
  </tr>
  <tr>
      <td>POST</td>
      <td>/addBucket/{bucketName}</td>
      <td>Add BucketName in Minio</td>
      <td></td>
      <td>[Info](#addBucketName)</td>
  </tr>
  <tr>
      <td>GET</td>
      <td>/show/{bucketName}</td>
      <td>Show defined Bucketname by its name in Minio</td>
      <td></td>
      <td>[Info](#showBucketName)</td>
  </tr>
  <tr>
      <td>GET</td>
      <td>/showBucketName</td>
      <td>Show all BucketNames in Minio</td>
      <td></td>
      <td>[Info](#showAllBucketName)</td>
  </tr>
  <tr>
      <td>DELETE</td>
      <td>/removeBucket/{bucketName}</td>
      <td>Delete defined bucketname from Minio</td>
      <td></td>
      <td>[Info](#deleteBucketName)</td>
  </tr>
  <tr>
       <td>DELETE</td>
       <td>/removeObject/{bucketName}/{objectName}</td>
       <td>Delete defined object in defined bucketname from Minio</td>
       <td></td>
       <td>[Info](#deleteBucketName)</td>
  </tr>
  <tr>
       <td>DELETE</td>
       <td>/removeListObject/{bucketName}</td>
       <td>Remove object list in defined bucketname from Minio</td>
       <td>[Info](#deleteListObject)</td>
       <td></td>
  </tr>
  <tr>
       <td>GET</td>
       <td>/showListObjectNameAndDownloadUrl/{bucketName}</td>
       <td>List object names and its download url in defined bucketname of Minio</td>
       <td></td>
       <td>[Info](#objectInformation)</td>
  </tr>
  <tr>
       <td>GET</td>
       <td>/download/{bucketName}/{objectName}</td>
       <td>Download object in BucketName from Minio</td>
       <td></td>
       <td>[Info](#download)</td>
  </tr>
</table>

### Used Dependencies
* Spring Boot Web
* Minio
* Lombok
* AspectJ
* Apache Commons Lang
* Swagger

## Swagger
> **Access : http://localhost:8085/swagger-ui.html**

## Valid Request Body

##### <a id="upload">Upload -> http://localhost:8085/minio/upload</a>
```
    file : Uploaded File
    bucketname : commons
```

##### <a id="deleteListObject">Delete List Object-> http://localhost:8085/minio/removeListObject/{bucketName}</a>
```
   [
       "de43ab54e89f4879a2baf87df1570f56.PNG",
       "f107737d21534f42a72dcf009a64a07d.PNG"
   ]
```

## Valid Request Params

##### <a id="addBucketName">Add Bucket Name -> http://localhost:8085/minio/addBucket/{bucketName}</a>
```
   http://localhost:8085/minio/addBucket/test1
```

##### <a id="showBucketName">Show Bucket Name -> http://localhost:8085/minio/show/{bucketName}</a>
```
   http://localhost:8085/minio/show/commons
```

##### <a id="showAllBucketName">Show All Bucket Names -> http://localhost:8085/minio/showBucketName</a>
```
   http://localhost:8085/minio/showBucketName
```

##### <a id="deleteBucketName">Delete Bucket Name -> http://localhost:8085/minio/removeBucket/{bucketName}</a>
```
   http://localhost:8085/minio/removeBucket/test1
```

##### <a id="objectInformation">List Object Information -> http://localhost:8085/minio/showListObjectNameAndDownloadUrl/{bucketName}</a>
```
   http://localhost:8085/minio/removeBucket/test1
```

##### <a id="download">Download File -> http://localhost:8085/minio/removeBucket/{bucketName}</a>
```
   http://localhost:8085/minio/download/commons/ad94ff2e9b404772a1f9b98f4e11b4f9.PNG
```

### Screenshots

<details>
<summary>Click here to show the screenshots of project</summary>
    <p> Figure 1 </p>
    <img src ="screenshots/screenshot_1.PNG">
    <p> Figure 2 </p>
    <img src ="screenshots/screenshot_2.PNG">
    <p> Figure 3 </p>
    <img src ="screenshots/screenshot_3.PNG">
    <p> Figure 4 </p>
    <img src ="screenshots/screenshot_4.PNG">
    <p> Figure 5 </p>
    <img src ="screenshots/screenshot_5.PNG">
    <p> Figure 6 </p>
    <img src ="screenshots/screenshot_6.PNG">
    <p> Figure 7 </p>
    <img src ="screenshots/screenshot_7.PNG">
    <p> Figure 8 </p>
    <img src ="screenshots/screenshot_8.PNG">
    <p> Figure 9 </p>
    <img src ="screenshots/screenshot_9.PNG">
    <p> Figure 10 </p>
    <img src ="screenshots/screenshot_10.PNG">
    <p> Figure 11 </p>
    <img src ="screenshots/screenshot_11.PNG">
    <p> Figure 12 </p>
    <img src ="screenshots/screenshot_12.PNG">
    <p> Figure 13 </p>
    <img src ="screenshots/screenshot_13.PNG">
    <p> Figure 14 </p>
    <img src ="screenshots/screenshot_14.PNG">
    <p> Figure 15 </p>
    <img src ="screenshots/screenshot_15.PNG">
    <p> Figure 16 </p>
    <img src ="screenshots/screenshot_16.PNG">
    <p> Figure 17 </p>
    <img src ="screenshots/screenshot_17.PNG">
    <p> Figure 18 </p>
    <img src ="screenshots/screenshot_18.PNG">
    <p> Figure 19 </p>
    <img src ="screenshots/screenshot_19.PNG">
    <p> Figure 20 </p>
    <img src ="screenshots/screenshot_20.PNG">
</details>