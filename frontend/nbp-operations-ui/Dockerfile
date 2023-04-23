FROM node:latest as builder

WORKDIR /usr/local/app

COPY ./ /usr/local/app/

RUN npm install
RUN npm run build

FROM nginx:latest

COPY --from=builder /usr/local/app/dist/nbp-operations-ui /usr/share/nginx/html

EXPOSE 4200
