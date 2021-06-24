# build environment
FROM node:12.2.0-alpine as build
WORKDIR /app
ENV PATH /app/node_modules/.bin:$PATH
ARG VUE_APP_BACKEND_URL
ARG VUE_APP_RELEASE_VERSION
ENV VUE_APP_RELEASE_VERSION=$VUE_APP_RELEASE_VERSION
ENV VUE_APP_BACKEND_URL=$VUE_APP_BACKEND_URL
COPY package.json /app/package.json
RUN npm install --silent
RUN npm install -g @vue/cli
COPY . /app
RUN npm run build

# production environment
FROM nginx:1.16.0-alpine
COPY --from=build /app/dist /usr/share/nginx/html
RUN rm /etc/nginx/conf.d/default.conf
COPY nginx/default.conf.template /etc/nginx/conf.d/default.conf.template
# Set the base url
ENV PROXY_API=$PROXY_API
EXPOSE 80
CMD /bin/sh -c "envsubst '\$PORT' < /etc/nginx/conf.d/default.conf.template > /etc/nginx/conf.d/default.conf" && nginx -g 'daemon off;'