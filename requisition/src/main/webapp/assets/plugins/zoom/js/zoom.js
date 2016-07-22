(function ($) {
    var body = $('body');
    body.append('<div id="zoom"><a class="close"></a><a href="#previous" class="previous"></a><a href="#next" class="next"></a><div class="content loading"></div></div>');

    var zoom = $('#zoom').hide(),
        zoomContent = $('#zoom .content'),
        overlay = '<div class="overlay"></div>',
        zoomedIn = false,
        openedImage = null,
        windowWidth = $(window).width(),
        windowHeight = $(window).height(),
        images = [];
    function open() {
        if (images.length == 0 || !openedImage) {
            return;
        }
        $('#zoom .previous, #zoom .next').show();
        if (images.length == 1) {
            $('#zoom .previous, #zoom .next').hide();
        }
        if (!zoomedIn) {
            zoomedIn = true;
            zoom.show();
            body.addClass('zoomed');
        }
        var image = $(new Image()).hide().css({ width: 'auto' });
        body.append(image);
        zoomContent.html('').delay(500).addClass('loading');
        zoomContent.prepend(overlay);
        image.load(render).attr('src', openedImage);

        function render() {
            var image = $(this),
                borderWidth = parseInt(zoomContent.css('borderLeftWidth')),
                maxImageWidth = windowWidth - (borderWidth * 2),
                maxImageHeight = windowHeight - (borderWidth * 2),
                imageWidth = image.width(),
                imageHeight = image.height();
            if (imageWidth == zoomContent.width() && imageWidth <= maxImageWidth && imageHeight == zoomContent.height() && imageHeight <= maxImageHeight) {
                show(image);
                return;
            }
            if (imageWidth > maxImageWidth || imageHeight > maxImageHeight) {
                var desiredHeight = maxImageHeight < imageHeight ? maxImageHeight : imageHeight,
                    desiredWidth = maxImageWidth < imageWidth ? maxImageWidth : imageWidth;
                if (desiredHeight / imageHeight <= desiredWidth / imageWidth) {
                    image.width(Math.round(imageWidth * desiredHeight / imageHeight));
                    image.height(desiredHeight);
                } else {
                    image.width(desiredWidth);
                    image.height(Math.round(imageHeight * desiredWidth / imageWidth));
                }
            }
            zoomContent.animate({
                width: image.width(),
                height: image.height(),
                marginTop: -(image.height() / 2) - borderWidth,
                marginLeft: -(image.width() / 2) - borderWidth
            }, 100, function () {
                show(image);
            });

            function show(image) {
                zoomContent.html(image);
                image.show();
                zoomContent.removeClass('loading');
            }
        }
    }

    function openPrevious() {
        if (images.length == 0) return;
        var index = 0;
        if (openedImage) {
            for (var i = 0; i < images.length; i++) {
                if (images[i] == openedImage) {
                    index = i - 1;
                    break;
                }
            }
            if (index < 0) {
                index = images.length - 1;
            }
        }
        openedImage = images[index];
        open();
    }

    function openNext() {
        if (images.length == 0) return;
        var index = 0;
        if (openedImage) {
            for (var i = 0; i < images.length; i++) {
                if (images[i] == openedImage) {
                    index = i + 1;
                    break;
                }
            }
            if (index >= images.length) {
                index = 0;
            }
        }
        openedImage = images[index];
        open();
    }

    function close(event) {
        if (event) {
            event.preventDefault();
        }
        zoomedIn = false;
        openedImage = null;
        zoom.hide();
        body.removeClass('zoomed');
        zoomContent.empty();
    }

    function changeImageDimensions() {
        windowWidth = $(window).width();
        windowHeight = $(window).height();
    }

    (function bindNavigation() {
        zoom.on('click', function (event) {
            event.preventDefault();
            if ($(event.target).attr('id') == 'zoom') {
                close();
            }
        });

        $('#zoom .close').on('click', close);
        $('#zoom .previous').on('click', openPrevious);
        $('#zoom .next').on('click', openNext);
        $(document).keydown(function (event) {
            if (!openedImage) {
                return;
            }
            if (event.which == 38 || event.which == 40) {
                event.preventDefault();
            }
            if (event.which == 27) {
                close();
            }
            if (event.which == 37 && !openedImage.hasClass('zoom')) {
                openPrevious();
            }
            if (event.which == 39 && !openedImage.hasClass('zoom')) {
                openNext();
            }
        });

        if ($('.gallery a').length == 1) {
            $('.gallery a')[0].addClass('zoom');
        }
        $('.zoom, .gallery a').on('click', open);
    })();

    (function bindChangeImageDimensions() {
        $(window).on('resize', changeImageDimensions);
    })();

    (function bindScrollControl() {
        $(window).on('mousewheel DOMMouseScroll', function (event) {
            if (!openedImage) {
                return;
            }
            event.stopPropagation();
            event.preventDefault();
            event.cancelBubble = false;
        });
    })();
    $.extend({
        initShowImage: function (imageDatas) {
            images = imageDatas;
            if (images.length > 0) {
                openedImage = images[0];
            }
            open();
        }
    });
})(jQuery);