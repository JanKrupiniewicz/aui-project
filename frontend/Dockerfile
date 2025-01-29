FROM nginx:1.27

ENV API_URL http://localhost:8080/api

EXPOSE 88

COPY dist/frontend/browser /usr/share/nginx/html/
ADD docker/etc/nginx/nginx.conf /etc/nginx/templates/
