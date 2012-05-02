<div id="nav" class="ui-widget-header ui-corner-all">
    <a class="ui-widget-header ui-corner-all" href="${createLinkTo(url: '/')}" id="homeMenu">Home</a>
    <a class="ui-widget-header ui-corner-all" href="#" id="bandMenu">Band</a>
    <a class="ui-widget-header ui-corner-all" href="#" id="gigMenu">Gig</a>
    <a class="ui-widget-header ui-corner-all" href="#" id="adminMenu">Admin</a>
</div>

<div id="bandMenuContent" style="display:none;">
    <ul>
        <li style="height: 15;"><a href="${createLink(controller: 'band')}">Información del grupo</a></li>
        <li style="height: 15;"><a href="${createLink(controller: 'member')}">Band Members</a></li>
        <li style="height: 15;"><a href="${createLink(controller: 'song')}">Songs</a></li>
    </ul>
</div>

<div id="gigMenuContent" style="display:none;">
    <ul>
        <li><a href="${createLink(controller: 'gig')}">Gig Information</a></li>
        <li><a href="${createLink(controller: 'setList')}">Set Lists</a></li>
    </ul>
</div>

<div id="adminMenuContent" style="display:none;">
    <ul>
        <li><a href="${createLink(controller: 'instrument')}">Instruments</a></li>
    </ul>
</div>
