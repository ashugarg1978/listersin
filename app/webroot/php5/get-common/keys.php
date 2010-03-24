<?php
    //show all errors - useful whilst developing
    error_reporting(E_ALL);

    // these keys can be obtained by registering at http://developer.ebay.com
    
    $production         = false;   // toggle to true if going against production
    $compatabilityLevel = 551;    // eBay API version
    
    if ($production) {
        $devID = 'DDD';   // these prod keys are different from sandbox keys
        $appID = 'AAA';
        $certID = 'CCC';
        //set the Server to use (Sandbox or Production)
        $serverUrl = 'https://api.ebay.com/ws/api.dll';      // server URL different for prod and sandbox
        //the token representing the eBay user to assign the call with
        $userToken = 'YOUR_PROD_TOKEN';          
    } else {  
        // sandbox (test) environment
        $devID = 'e60361cd-e306-496f-ad7d-ba7b688e2207';         // insert your devID for sandbox
        $appID = 'Yoshihir-1b29-4aad-b39f-1be3a37e06a7';   // different from prod keys
        $certID = '8118c1eb-e879-47f3-a172-2b08ca680770';  // need three 'keys' and one token
        //set the Server to use (Sandbox or Production)
        $serverUrl = 'https://api.sandbox.ebay.com/ws/api.dll';
        // the token representing the eBay user to assign the call with
        // this token is a long string - don't insert new lines - different from prod token
        $userToken = 'AgAAAA**AQAAAA**aAAAAA**Z6N2Sw**nY+sHZ2PrBmdj6wVnY+sEZ2PrA2dj6wFk4CoD5mFow6dj6x9nY+seQ**Q0UBAA**AAMAAA**JJDsfE+wQFK+EWdZ26syt/D3f1YfYL236IUFs4qTucxrgq0cSr5Pk0YD35LuwFQHLwwJ6Mbz3l+qsQZkF05V/WbkYgbvLu8HG6AtdtL6VdIo941fiW9+XFOvdMgdPI+zFnSp7HNec9+f0plQ7tutQUuu2/t5GmWEEFB3hP9jDVDZ5nD/uNY+332WybjTSwkUUwTQW74fcnEZJB8Afr+gYk9kt71yD5iZMMc6Pbj38FcrYPDLIrhIh5yC7S/Z3EGkUxqPqJuC0SO6iisuG4wG28zJ5vJqQ4lfa+M9VIlOhBADdo2KFaF37wVR0sakHBmmXRDRNfJ5M4DxHX0mW1Y9gHXrjO9IRuG77kjeCA+yvauYJU/QMuidcftlP/QwXZEd4oI1nj/fUyPZbtNMbichZn/5AmEfpJfD3tRkfEyW9QL0Xobw4i5QOi8MVSE2jh+3OjK4J0iKK4naQ9Xiv43uOjPTVbR6Ii3hgGBC+nOXVqgh2DmQpX4vznTl92f5WEyFuNZpLuiyNs3T/QbQ/7ps5POBAnfCwDQMSVBdvRPPOxcXwb8D8uPk2H71VarDxPFYzpew/KVBjpB1nhwXvXWT5uKyq86cYQSanEK7QxUDGu5PcTT2eYB/cZ4lDoviF135Z91ULc4WiVzUpeoHQ7et+eED7bw4VD0HeFfTKwqoQ0VZvPfs3IVhLGswtrlrWmklmMUBuy2d4GF3UInZOUvkkcKw7Sk/nmfLhueixz4iQkWDSi+wxeOumVk86mNoc93H';                 
    }
    
    
?>