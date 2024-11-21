/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
$(document).ready(function() {

    "use strict";

    window.onscroll = function() {setStickTop()};
    var navbar = document.getElementById("navbar");
    if (navbar !== null) {
        var sticky = navbar.offsetTop;
    }

    $(".cartItemQty").on('change', function(){
        var id=this.id;

        $('#update-item-'+id).css('display', 'inline-block');
    });


    $("#theSameAsShippingAddress").on('click', checkBillingAddress);
    $("#txtConfirmPassword").keyup(checkPasswordMatch);
    $("#txtNewPassword").keyup(checkPasswordMatch);

    getAQuote();
    getShowOrHiddenCategoryMenu();
    getTooltips();
    initViewedSlider();
    initBrandsSlider();
    initReviewsSlider();
    initTrendsSlider();
    bestsellersSlider();
    initArrivalsSlider();
    initPopularSlider();

    function getAQuote() {

        var get_a_quote = $('.get-a-quote');

        get_a_quote.on('click', function (){

            /*<![CDATA[*/
            var path = /*[[@{/}]]*/'/getAQoute';
            /*]]>*/

            var id=$(this).attr('id');

            bootbox.confirm({
                message: "Are you sure, that do you want get a qoute? It can't be undone.",
                buttons: {
                    cancel: {
                        label:'<i class="fa fa-times"></i> Cancel'
                    },
                    confirm: {
                        label:'<i class="fa fa-check"></i> Confirm'
                    }
                },
                callback: function(confirmed) {
                    if(confirmed) {
                        $(location).attr('href', path)
                    }
                }
            });
        });
    }

    function getShowOrHiddenCategoryMenu() {

        var menu_container = $('.cat_menu_container');
        var menu_hidden = $('#cat_menu_hidden');

        menu_container.mouseover(function() {
            menu_hidden.show();
        }).mouseout(function() {
            menu_hidden.hide();
        })
    }

    function setStickTop() {
        if (navbar !== null) {
            if (window.pageYOffset >= sticky) {
                navbar.classList.add("sticky")
            } else {
                navbar.classList.remove("sticky");
            }
        }
    }

    function checkBillingAddress() {
        if($("#theSameAsShippingAddress").is(":checked")) {
            $(".billingAddress").prop("disabled", true);



        } else {
            $(".billingAddress").prop("disabled", false);



        }
    }

    function checkPasswordMatch() {
        var password = $("#txtNewPassword").val();
        var confirmPassword = $("#txtConfirmPassword").val();

        if(password === "" && confirmPassword ==="") {
            $("#checkPasswordMatch").html("");
            $("#updateUserInfoButton").prop('disabled', false);
        } else {
            if(password !== confirmPassword) {
                $("#checkPasswordMatch").html("Passwords do not match!");
                $("#updateUserInfoButton").prop('disabled', true);
            } else {
                $("#checkPasswordMatch").html("Passwords match");
                $("#updateUserInfoButton").prop('disabled', false);
            }
        }
    }

    function getTooltips () {
        $('[data-toggle="tooltip"]').tooltip();
    }

	// Init Recently Viewed Slider
    function initViewedSlider() {
        if($('.viewed_slider').length)
        {
            var viewedSlider = $('.viewed_slider');

            viewedSlider.owlCarousel(
                {
                    loop:true,
                    margin:30,
                    autoplay:true,
                    autoplayTimeout:6000,
                    nav:false,
                    dots:false,
                    responsive:
                        {
                            0:{items:1},
                            575:{items:2},
                            768:{items:3},
                            991:{items:4},
                            1199:{items:6}
                        }
                });

            if($('.viewed_prev').length)
            {
                var prev = $('.viewed_prev');
                prev.on('click', function()
                {
                    viewedSlider.trigger('prev.owl.carousel');
                });
            }

            if($('.viewed_next').length)
            {
                var next = $('.viewed_next');
                next.on('click', function()
                {
                    viewedSlider.trigger('next.owl.carousel');
                });
            }
        }
    }

    // Init Brands Slider
    function initBrandsSlider() {
        if($('.brands_slider').length)
        {
            var brandsSlider = $('.brands_slider');

            brandsSlider.owlCarousel(
                {
                    loop:true,
                    autoplay:true,
                    autoplayTimeout:5000,
                    nav:false,
                    dots:false,
                    autoWidth:true,
                    items:8,
                    margin:30
                });

            if($('.brands_prev').length)
            {
                var prev = $('.brands_prev');
                prev.on('click', function()
                {
                    brandsSlider.trigger('prev.owl.carousel');
                });
            }

            if($('.brands_next').length)
            {
                var next = $('.brands_next');
                next.on('click', function()
                {
                    brandsSlider.trigger('next.owl.carousel');
                });
            }
        }
    }

    // Init Reviews Slider
    function initReviewsSlider() {
        if($('.reviews_slider').length)
        {
            var reviewsSlider = $('.reviews_slider');

            reviewsSlider.owlCarousel(
                {
                    items:3,
                    loop:true,
                    margin:30,
                    autoplay:true,
                    autoplayTimeout:5000,
                    nav:false,
                    dots:true,
                    dotsContainer: '.reviews_dots',
                    responsive:
                        {
                            0:{items:1},
                            768:{items:2},
                            991:{items:3}
                        }
                });
        }
    }


    // Init Trends Slider
    function initTrendsSlider()
    {
        if($('.trends_slider').length)
        {
            var trendsSlider = $('.trends_slider');
            trendsSlider.owlCarousel(
                {
                    loop:false,
                    margin:30,
                    nav:false,
                    dots:false,
                    autoplayHoverPause:true,
                    autoplay:false,
                    responsive:
                        {
                            0:{items:1},
                            575:{items:2},
                            991:{items:3}
                        }
                });

            trendsSlider.on('click', '.trends_fav', function (ev)
            {
                $(ev.target).toggleClass('active');
            });

            if($('.trends_prev').length)
            {
                var prev = $('.trends_prev');
                prev.on('click', function()
                {
                    trendsSlider.trigger('prev.owl.carousel');
                });
            }

            if($('.trends_next').length)
            {
                var next = $('.trends_next');
                next.on('click', function()
                {
                    trendsSlider.trigger('next.owl.carousel');
                });
            }
        }
    }


    // Init Best Sellers Slider
    function bestsellersSlider()
    {
        if($('.bestsellers_slider').length)
        {
            var bestsellersSliders = $('.bestsellers_slider');
            bestsellersSliders.each(function()
            {
                var bestsellersSlider = $(this);

                initBSSlider(bestsellersSlider);
            })
        }
    }

    function initBSSlider(bss)
    {
        var bestsellersSlider = bss;

        bestsellersSlider.slick(
            {
                rows:2,
                infinite:true,
                slidesToShow:3,
                slidesToScroll:3,
                arrows:false,
                dots:true,
                autoplay: true,
                autoplaySpeed: 6000,
                responsive:
                    [
                        {
                            breakpoint:1199, settings:
                                {
                                    rows:2,
                                    slidesToShow:2,
                                    slidesToScroll:2,
                                    dots:true
                                }
                        },
                        {
                            breakpoint:991, settings:
                                {
                                    rows:2,
                                    slidesToShow:1,
                                    slidesToScroll:1,
                                    dots:true
                                }
                        },
                        {
                            breakpoint:575, settings:
                                {
                                    rows:1,
                                    slidesToShow:1,
                                    slidesToScroll:1,
                                    dots:false
                                }
                        }
                    ]
            });
    }


    // Init Arrivals Slider
    function initArrivalsSlider()
    {
        if($('.arrivals_slider').length)
        {
            var arrivalsSliders = $('.arrivals_slider');
            arrivalsSliders.each(function()
            {
                var arrivalsSlider = $(this);
                initASlider(arrivalsSlider);
            });

        }
    }

    function initASlider(as)
    {
        var arrivalsSlider = as;
        arrivalsSlider.on('init', function()
        {
            var activeItems = arrivalsSlider.find('.slick-slide.slick-active');
            for(var x = 0; x < activeItems.length - 1; x++)
            {
                var item = $(activeItems[x]);
                item.find('.border_active').removeClass('active');
                if(item.hasClass('slick-active'))
                {
                    item.find('.border_active').addClass('active');
                }
            }
        }).on(
            {
                afterChange: function(event, slick, current_slide_index, next_slide_index)
                {
                    var activeItems = arrivalsSlider.find('.slick-slide.slick-active');
                    activeItems.find('.border_active').removeClass('active');
                    for(var x = 0; x < activeItems.length - 1; x++)
                    {
                        var item = $(activeItems[x]);
                        item.find('.border_active').removeClass('active');
                        if(item.hasClass('slick-active'))
                        {
                            item.find('.border_active').addClass('active');
                        }
                    }
                }
            })
            .slick(
                {
                    rows:2,
                    slidesToShow:5,
                    slidesToScroll:5,
                    infinite:false,
                    arrows:false,
                    dots:true,
                    responsive:
                        [
                            {
                                breakpoint:768, settings:
                                    {
                                        rows:2,
                                        slidesToShow:3,
                                        slidesToScroll:3,
                                        dots:true
                                    }
                            },
                            {
                                breakpoint:575, settings:
                                    {
                                        rows:2,
                                        slidesToShow:2,
                                        slidesToScroll:2,
                                        dots:false
                                    }
                            },
                            {
                                breakpoint:480, settings:
                                    {
                                        rows:1,
                                        slidesToShow:1,
                                        slidesToScroll:1,
                                        dots:false
                                    }
                            }
                        ]
                });
    }


    // Init Arrivals Slider ZIndex
    function arrivalsSliderZIndex()
    {
        // Hide slider dots on item hover
        var items = document.getElementsByClassName('arrivals_slider_item');

        for(var x = 0; x < items.length; x++)
        {
            var item = items[x];
            item.addEventListener('mouseenter', function()
            {
                $('.arrivals_slider .slick-dots').css('display', "none");
            });

            item.addEventListener('mouseleave', function()
            {
                $('.arrivals_slider .slick-dots').css('display', "block");
            });
        }
    }


    //  Init Popular Categories Slider
    function initPopularSlider()
    {
        if($('.popular_categories_slider').length)
        {
            var popularSlider = $('.popular_categories_slider');

            popularSlider.owlCarousel(
                {
                    loop:true,
                    autoplay:false,
                    nav:false,
                    dots:false,
                    responsive:
                        {
                            0:{items:1},
                            575:{items:2},
                            640:{items:3},
                            768:{items:4},
                            991:{items:5}
                        }
                });

            if($('.popular_categories_prev').length)
            {
                var prev = $('.popular_categories_prev');
                prev.on('click', function()
                {
                    popularSlider.trigger('prev.owl.carousel');
                });
            }

            if($('.popular_categories_next').length)
            {
                var next = $('.popular_categories_next');
                next.on('click', function()
                {
                    popularSlider.trigger('next.owl.carousel');
                });
            }
        }
    }


});

function getPdfContent(test) {
    window.open(test);
}


