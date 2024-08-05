
    <h1>HR & Inventory Management System</h1>

    <h2>Backend System Environment (JAVA-Spring boot)</h2>
    <ul>
        <li>Java JDK 17</li>
        <li>Set Up: IntelliJ IDE</li>
        <li>MySQL Server Version: 8.0.36</li>
        <li>PHP Version: 7.4.26</li>
        <li>phpMyAdmin SQL: version 5.1.1</li>
        <li>OS: win32 x64</li>
    </ul>

    <h2>Frontend System Environment (TypeScript-Angular)</h2>
    <ul>
        <li>Angular CLI: 17.2.1</li>
        <li>Set Up: WebStorm IDE or VS Code IDE</li>
        <li>Node version: 20.11.1</li>
        <li>npm version: 10.2.4</li>
        <li>OS: win32 x64</li>
    </ul>

    <h3>Important Things</h3>
    <p>In your operating system, you do not have the above requirements????</p>
    <p>Follow these steps:</p>

    <h4>Backend Setup Steps: (Download and Install)</h4>
    <ol>
        <li>Java JDK 17: Download from the 
            <a href="https://www.oracle.com/java/technologies/downloads/#jdk17-windows">Oracle JDK website</a>
        </li>
        <li>IntelliJ IDE: Download and install IntelliJ IDEA from the 
            <a href="https://www.jetbrains.com/idea/download/?section=windows">JetBrains website</a>
        </li>
        <li>MySQL Server 8.0.36: Download from the 
            <a href="https://dev.mysql.com/downloads/mysql/8.0.html">MySQL website</a>
        </li>
        <li>OR WAMP Server: 
            <a href="https://sourceforge.net/projects/wampserver/">https://sourceforge.net/projects/wampserver/</a>
        </li>
    </ol>

    <h4>Frontend Setup Steps: (Download and Install)</h4>
    <ol>
        <li>Angular CLI 17.2.1: Open Command Prompt and install using npm:
            <pre>('npm install -g @angular/cli@17.2.1')</pre>
        </li>
        <li>WebStorm IDE or VS Code IDE: Download WebStorm from the 
            <a href="https://www.jetbrains.com/webstorm/download/#section=windows">JetBrains website</a>
            or download Visual Studio Code from the 
            <a href="https://code.visualstudio.com/Download">VS Code website</a>
        </li>
        <li>Node.js 20.11.1: Download from the 
            <a href="https://nodejs.org/en/download/package-manager">Node.js website</a>
        </li>
        <li>npm 10.2.4: npm is included with Node.js. After installing Node.js, verify npm version with 'npm -v'.
            If needed, update npm:
            <pre>('npm install -g npm@10.2.4')</pre>
        </li>
    </ol>

    <hr>
    <h3>After Setup Above These Things</h3>

    <p>Extract "Project" Folder</p>
    <ol>
        <li>Open The "BackEnd" Folder. It has System Backend. Open this Project Using IntelliJ IDE. Maven loading.... after run the Spring Boot Project.</li>
        <li>After running the BackEnd Project, the database setup will be automatically done with admin user details:
            <ul>
                <li>Username: admin</li>
                <li>Password: admin1234</li>
            </ul>
        </li>
        <li>If you want to include sample employee data, open the "Employee Sample Data" folder. This folder has an SQL file which can be imported into the database using phpMyAdmin.</li>
        <li>If you want to include employees via the front end, log in to the admin account (HR_Manager) and add new employees.</li>
        <li>Open the "FrontEnd" folder. It has System Frontend. Open this project using WebStorm or VS Code.
            <ul>
                <li>If you have WebStorm IDE:
                    <ul>
                        <li>Open this project -> IDE shows npm install button -> click this</li>
                        <li>After setup, run the command -> 'ng serve'</li>
                    </ul>
                </li>
                <li>If you have VS Code IDE:
                    <ul>
                        <li>Open this project -> setup npm install</li>
                        <li>After setup, run the command -> 'ng serve'</li>
                    </ul>
                </li>
            </ul>
        </li>
        <li>After running the frontend program, the IDE will show the URL: 
            <a href="http://localhost:4200/">http://localhost:4200/</a>
        </li>
        <li>Click this URL to navigate to the login page.</li>
        <li>Log in to the Admin (HR_Manager) account:
            <ul>
                <li>Username: admin</li>
                <li>Password: admin1234</li>
            </ul>
        </li>
        <li>Now you have only HR_Manager account. Admin can create system users by navigating to the side menu bar -> Manage Users. Create new users for including employee details for each separate user.
            <ul>
                <li>User: Accountant</li>
                <li>User: Production_Manager</li>
                <li>User: Store_Keeper</li>
            </ul>
            The system will send the mail with username and password.
        </li>
        <li>After creating each user account, log in to their dashboards and perform their functionalities.
            <p>Created user account default password is "Employee ID".</p>
        </li>
    </ol>
