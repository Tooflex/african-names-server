# stage 1
FROM node:16-alpine AS my-app-build
WORKDIR /app
COPY . .
RUN npm ci -timeout=9999999
RUN npm run build:prod

# stage 2
FROM nginx:alpine
COPY nginx.conf /etc/nginx/conf.d/default.conf
RUN rm -rf /usr/share/nginx/html/*
COPY --from=my-app-build /app/dist/angular-front /usr/share/nginx/html/
EXPOSE 80
