#!/bin/bash

# create the group, the -f option causes the command to return 0 even if the group already exists
groupadd -fr ${app.service.user}
# create the user if the user does not exist
id -u ${app.service.user} &> /dev/null || useradd -r -g ${app.service.user} ${app.service.user}
