from django.conf.urls import patterns,url

from API import views

urlpatterns=patterns('API.views',
   url(r'fb_register', 'fb_registration'),
   url(r'register','registration'),
   url(r'login','login'),
   url(r'push/data','push_data'),
   url(r'getCarPoolers', 'getCarPoolers'),
   url(r'profile', 'save_profile'),
   url(r'send/message', 'send_message'),
   url(r'fetch/message', 'fetch_message'),
)
