![logo](https://em2.storage.oraclecloud.com/v1/storagetrial5906-ieoracletrial87560/static/oracle-cloud-logo.png)

# Oracle Storage Cloud Service 

## Serving Static Content for Java EE application.

Oracle Storage Cloud Service is based on OpenStack SWIFT (Object Storage). 
This sample project shows 3 different ways to serve static content:

* "Hard coding" the URL associated to the static content.

* Using a constant in JSP.

* Using a Servlet to retrieve the static content without changing any code.

In 3rd option, the sample retrieves static content from Oracle Storage Cloud whenever a request to /static/<content> is created.
File /WEB-INF/object-storage.xml users can be used to change the Service URL Endpoint for Oracle Storage Cloud Service. URL is formed as follows:

    https://<datacenter>.storage.oraclecloud.com/v1/<service_name>-<identity_domain>/

Containers and objects follow the same standards as OpenStack SWIFT

    <URL>/<Container>/[folder|object]

To retrieve the data without changing the code, we created a Servlet that handles the requests to /static and forwards them to Oracle Storage Cloud Service. The mapping is made in web.xml file.

    <servlet-mapping>
        <servlet-name>StaticServlet</servlet-name>
        <url-pattern>/static/*</url-pattern>
    </servlet-mapping>

It's important to note that static content needs to be uploaded in the same folder structure to Storage Cloud Service in order to minimize code changes.
Uploading data can be done through [Oracle Storage Cloud Java SDK](http://www.oracle.com/technetwork/topics/cloud/downloads/cloud-service-java-sdk-2121032.html), [REST API](http://docs.oracle.com/cloud/latest/storagecs_common/index.html) or other OpenStack SWIFT compatible technologies such as [CloudBerry Explorer](http://www.cloudberrylab.com/blog/how-to-use-cloudberry-explorer-with-oracle-cloud-storage/), [python-swiftclient](https://github.com/openstack/python-swiftclient) module for Python, etc.

## Additional Info
* [Oracle Cloud Platform](http://cloud.oracle.com/)
* [Oracle Storage Cloud Service](http://cloud.oracle.com/storage)
* [Documentation] (http://docs.oracle.com/cloud/latest/storagecs_common/index.html)

## Disclaimer

The sample code is intended for demo purposes only and no endorsement of Oracle implied. 

We provide these sample codes to demonstrate capabilities and use cases, but they are provided as is, without any guarantees that they will work in all system configurations and different versions of software.  
These snippets have been tested in our environments and worked succesfully for us, but we expect you to have proper technical knowledge to address any system or coding issues that arise from using these sample codes.

@author Juan Carlos Ruiz Rico <juan.carlos.r.ruiz.rico@oracle.com>
@date 12/22/2015
