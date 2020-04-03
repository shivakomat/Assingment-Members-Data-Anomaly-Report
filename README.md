<b>Settings</b>

The application code is packaged into a jar file which is executable by scala version 2.12. The jar file also includes 
all the dependency libraries needed to execute the app. So that leaves use with the only dependency to be installed to run
the jar is the scala verison 2.12

<b> How to install scala ? </b> 

Assuming that you already have brew installed

1 ) brew install scala@2.12 (try scala -version)
2 ) export PATH="/usr/local/opt/scala@2.12/bin:$PATH" (Optional)


<b> How to run the app ? </b> 

<u>Folder Structure</u>
-Assignment
  -\lib
    --ChimeMembersAnalysisAssignment-assembly-0.1.jar
  -\data
    --member_records_data_sets.json

Once Scala 2.12 is installed with brew. We can run the app in the following format. Please take a look.

<b> scala ./lib/ChimeMembersAnalysisAssignment-assembly-0.1.jar --membersDataset ./data/member_records_data_sets.json 
--invalidMembersOutput ./invalid_records.csv  --deletedRecordsOutput ./deleted_records.csv </b>

---------------Members Report App------------------------------
Usage: Members Report Analysis APP [options]

  -m, --membersDataset <value>
                           members data set input path
  -i, --invalidMembersOutput <value>
                           invalid members output path
  -d, --deletedRecordsOutput <value>
                           deleted records output path

<b> Assignment Details </b>

Programming Language / Enviroment used is Scala
Developed using Intelli J 
