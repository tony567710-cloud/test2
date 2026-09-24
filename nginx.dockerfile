FROM nginx:1.30.5

ENV TZ="Asia/Taipei"

ARG PUBLISH_DIR=dist
COPY ./${PUBLISH_DIR} /usr/share/nginx/html

CMD ["nginx", "-g", "daemon off;"]