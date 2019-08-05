<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>
<div role="alert" aria-live="assertive" aria-atomic="true" class="toast" data-autohide="true">
    <div class="toast-header">
        <img src="${image}" class="rounded mr-2" alt="...">
        <strong class="mr-auto">${title}</strong>
        <small>Now</small>
        <button type="button" class="ml-2 mb-1 close" data-dismiss="toast" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>
    <div class="toast-body">
        ${text}
    </div>
</div>
