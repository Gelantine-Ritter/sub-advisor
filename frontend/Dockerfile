FROM node:lts-alpine

WORKDIR /src

ADD package.json /src

RUN npm install --progress=false

ADD . /src

RUN npm run build

CMD [ "npm", "run", "serve", "--port ${PORT}"]