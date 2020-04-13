<b>Problem statement</b>

Acme, Inc. started business in 2013 and immediately started onboarding members.  

Enrollment business rules:

You must be 18 years or older to have an account at Acme, Inc.
You must provide valid identifiers (email, zip code, phone number) during enrollment.
Members should never be removed (i.e. deleted), they should be marked as cancelled.
Attached is a DB schema for storing the input, the data should conform completely to the schema.

A developer wrote a script that accidentally messed up some data and there have also been a few bugs over the years that could 
have caused issues, meaning the data may have stopped adhering to the business rules above.

When casually looking through some of that members data, some issues were identified. Now we'd like to have a comprehensive 
analysis done on the data to find any anomalies.

<b>Solution</b>

The application solves it by validating each row of the data from the data set and outputting two files. One file representing
the invalid members based on the validations check and another file representing the deleted records. Please see the settings section and the how to run the app section to get your results and testing against other members data sets.


<b>Settings</b>

The application code is packaged into a jar file which is executable by scala version 2.12. The jar file also includes 
all the dependency libraries needed to execute the app. So that leaves use with the only dependency to be installed to run
the jar is the scala verison 2.12


<b> How to run the app ? </b> 

<b>Folder Structure</b>
```
-Assignment
  -\lib
    --ChimeMembersAnalysisAssignment-assembly-0.1.jar
  -\data
    --member_records_data_sets.json
```

Once Scala 2.12 is installed with brew. We can run the app in the following format. Please take a look.
```
scala ./lib/ChimeMembersAnalysisAssignment-assembly-0.1.jar --membersDataset ./data/member_records_data_sets.json 
--invalidMembersOutput ./invalid_records.csv  --deletedRecordsOutput ./deleted_records.csv
```

```

---------------Members Report App------------------------------
Usage: Members Report Analysis APP [options]

  -m, --membersDataset <value>
                           members data set input path
  -i, --invalidMembersOutput <value>
                           invalid members output path
  -d, --deletedRecordsOutput <value>
                           deleted records output path
```
