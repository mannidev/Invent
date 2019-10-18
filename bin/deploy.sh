SERVER_PATH="/c/Program Files/Apache Software Foundation/Tomcat 9.0"
PROJECT_PATH=/c/Users/emmanuel.kuatsidzo/source/repos/Java/eclipse-workspace/Inventory
PROJECT_NAME=Inventory_v1

##Create project directory if it does not exist
if [ ! -d "$SERVER_PATH/webapps/$PROJECT_NAME" ]; then
    echo "creating project directory..."
    mkdir "$SERVER_PATH/webapps/$PROJECT_NAME"
    mkdir "$SERVER_PATH/webapps/$PROJECT_NAME/WEB-INF" 
fi

##copy web files 
echo "copying web files..."
rm -rf "$SERVER_PATH/webapps/$PROJECT_NAME/css"
mkdir "$SERVER_PATH/webapps/$PROJECT_NAME/css" 

for file in $PROJECT_PATH/web/css/*.css; do 
    if [ -f "$PROJECT_PATH/web/css/$(basename "$file")" ]; then
        cp $PROJECT_PATH/web/css/*.css "$SERVER_PATH/webapps/$PROJECT_NAME/css"
    fi
done

rm -rf "$SERVER_PATH/webapps/$PROJECT_NAME/js"
mkdir "$SERVER_PATH/webapps/$PROJECT_NAME/js"

for file in $PROJECT_PATH/web/js/*.js; do
    if [ -f "$PROJECT_PATH/web/js/$(basename "$file")" ]; then
        cp $PROJECT_PATH/web/js/*.js "$SERVER_PATH/webapps/$PROJECT_NAME/js"
    fi
done

rm -rf "$SERVER_PATH/webapps/$PROJECT_NAME/img"
mkdir "$SERVER_PATH/webapps/$PROJECT_NAME/img"

for file in $PROJECT_PATH/web/img/*; do
    if [ -f "$PROJECT_PATH/web/img/$(basename "$file")" ]; then
        cp $PROJECT_PATH/web/img/* "$SERVER_PATH/webapps/$PROJECT_NAME/img"
    fi
done

cp $PROJECT_PATH/web/*.jsp "$SERVER_PATH/webapps/$PROJECT_NAME/"
cp $PROJECT_PATH/web/*.html "$SERVER_PATH/webapps/$PROJECT_NAME/"

##copy deployment descriptors 
echo "copying deployment descriptors..."
cp $PROJECT_PATH/etc/web.xml "$SERVER_PATH/webapps/$PROJECT_NAME/WEB-INF/"

##compile java files
echo "compiling java files"
javac -classpath "$SERVER_PATH/lib/servlet-api.jar" -d $PROJECT_PATH/classes $PROJECT_PATH/src/com/inventory/**/*.java

##create class packages
echo "removing existing packages"
rm -rf "$SERVER_PATH/webapps/$PROJECT_NAME/WEB-INF/classes"

echo "creating class packages directories..."
mkdir "$SERVER_PATH/webapps/$PROJECT_NAME/WEB-INF/classes"
mkdir "$SERVER_PATH/webapps/$PROJECT_NAME/WEB-INF/classes/com"
mkdir "$SERVER_PATH/webapps/$PROJECT_NAME/WEB-INF/classes/com/inventory"
mkdir "$SERVER_PATH/webapps/$PROJECT_NAME/WEB-INF/classes/com/inventory/model"
mkdir "$SERVER_PATH/webapps/$PROJECT_NAME/WEB-INF/classes/com/inventory/web"
mkdir "$SERVER_PATH/webapps/$PROJECT_NAME/WEB-INF/classes/com/inventory/util"

##copy class files
echo "copying class files"
cp $PROJECT_PATH/classes/com/inventory/web/*.class "$SERVER_PATH/webapps/$PROJECT_NAME/WEB-INF/classes/com/inventory/web/"
cp $PROJECT_PATH/classes/com/inventory/model/*.class "$SERVER_PATH/webapps/$PROJECT_NAME/WEB-INF/classes/com/inventory/model/"
cp $PROJECT_PATH/classes/com/inventory/util/*.class "$SERVER_PATH/webapps/$PROJECT_NAME/WEB-INF/classes/com/inventory/util/"

##copy external lib files 
echo "copying external libs"
cp $PROJECT_PATH/lib/*.jar "$SERVER_PATH/lib/"

##stop and start tomcat service
net stop Tomcat9
net start Tomcat9