# get the base node image
FROM node:14

# set the working dir for container
WORKDIR /frontend

# copy the json file first
COPY ./package.json /frontend

# install npm dependencies
RUN npm install npm@8.12.1

# copy other project files
COPY . .

# build the folder
CMD [ "npm", "run", "start" ]