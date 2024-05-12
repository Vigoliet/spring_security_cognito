
# Project - AWS Cognito


## Set up


Create a .env file in the root of the project and to create and configure a user pool in AWS cognito, also make sure to create
a local or an AWS database, and add the credentials to the `.env` file.

``` Env
CLIENT_ID=
CLIENT_SECRET=
ISSUER_URI=
POOL_ID=
CLIENT_ADMIN_ID=
DATABASE_USERNAME=
DATABASE_PASSWORD=
DATABASE_URL=
```

## Development process and challenges
1. **AWS Cognito** - Setting up the AWS Cognito and creating the user pool.
2. **Java** - Creating the classes and methods for the system
3. **Testing** - Testing the system and fixing bugs, with AWS connections and local connections.
4. **GUI** - Creating the GUI for the system, through the console application. Step 3 and 4 was done in parallel.
5. **Adding features** - Adding features such as env. files.
6. **Documentation** - Writing the documentation for the system

### Challenges - that I faced during the development process
1. One of the biggest challenges was when trying to overwrite the Cognito sign up, since I disabled it in the AWS console
it lead to many bugs were I didn't have the correct permissions to create a user.

2. Another challenge was when removing a user and its corresponding data, it didn't log out the user so the user could still add tasks.





## Things to note

>Note that since we're creating a new user using "AdminCreateUser" API, the user will be in `FORCE_CHANGE_PASSWORD` status. The user will be prompted to change the password after the first login.

<img src="https://docs.aws.amazon.com/images/cognito/latest/developerguide/images/amazon-cognito-sign-in-confirm-user.png">
