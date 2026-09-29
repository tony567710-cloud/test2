FROM caddy:2.11.4

ENV TZ="Asia/Taipei"

COPY ./Caddyfile /etc/caddy/Caddyfile
